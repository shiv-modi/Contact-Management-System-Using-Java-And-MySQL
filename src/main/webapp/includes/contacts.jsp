<%@page import="java.io.OutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.cms.db.contacts_db"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.db.dbUtil"%>
<%@ page import="java.util.Base64" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS | Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
     <link rel="stylesheet" href="../css/style.css">
</head>
<body class="bg-gray-100">

	<%! int id;
		int i = 0;
		%>

	<% 
		HttpSession session2 = request.getSession(false);
		if (session2 != null && session2.getAttribute("id") != null) {
		    id = (int) session2.getAttribute("id");
		    session2.setAttribute("id", id); 
		} else {
		    response.sendRedirect("../index.jsp");
		    return; 
		}
	%>
    <div class="min-h-screen flex flex-col lg:flex-row">
        
        <aside id="sidebar" class="sidebar expanded bg-blue-600 text-white p-6">
     	<div class="flex justify-center mb-12">
                <img src="../img/logo.jpeg" alt="Logo" class="contact-images">
            </div>
			<nav class="flex flex-col h-full">
			    <ul class="space-y-6 text-lg flex-grow">
			        <li>
			            <a href="#" class="flex items-center py-3 px-4 bg-blue-700 hover:bg-blue-800 transition duration-300 rounded-lg">
			                <i class="fas fa-tachometer-alt mr-4"></i>
			                <span class="sidebar-text flex-1">Dashboard</span>
			            </a>
			        </li>
					<li>
					    <a href="#" id="importBtn" class="flex items-center py-3 px-4 hover:bg-blue-700 transition duration-300 rounded-lg">
					        <i class="fas fa-file-import mr-3"></i>
					        <span class="sidebar-text flex-1">Import Contacts</span>
					    </a>
					</li>

					<li>
					    <a href="../exportContacts" class="flex items-center py-3 px-4 hover:bg-blue-700 transition duration-300 rounded-lg">
					        <i class="fas fa-file-export mr-3"></i>
					        <span class="sidebar-text flex-1">Export Contacts</span>
					    </a>
					</li>

			        <li>
			            <a href="#" class="flex items-center py-3 px-4 hover:bg-blue-700 transition duration-300 rounded-lg">
			                <i class="fa fa-calendar mr-3"></i>
			                <span class="sidebar-text flex-1">Reminder</span>
			            </a>
			        </li>
			
			        <li class="mt-auto">
			            <a href="logout.jsp" class="flex items-center py-3 px-4 hover:bg-red-600 bg-red-500 transition duration-300 rounded-lg">
			                <i class="fas fa-sign-out-alt mr-3"></i>
			                <span class="sidebar-text flex-1">Logout</span>
			            </a>
			        </li>
			    </ul>
			</nav>
        </aside>

     <main class="flex-1 p-8 lg:p-12 bg-gray-50">
            <!-- Search and Add New Button -->
            <div class="flex flex-col lg:flex-row justify-between items-center mb-10 space-y-4 lg:space-y-0">
                <div class="flex items-center space-x-4 w-full lg:w-auto">
                    <input type="text" placeholder="Search contacts..." class="border border-gray-300 p-3 rounded-lg w-full lg:w-96 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <button class="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700 transition duration-300">Search</button>
                </div>
                <button id="mainAddContactButton" class="bg-green-500 text-white px-6 py-3 rounded-lg hover:bg-green-600 transition duration-300 flex items-center cursor-pointer">
                    <i class="fas fa-user-plus mr-2"></i> Add Contact
                </button>
            </div>


          <div class="bg-white shadow-lg rounded-lg p-6">
    <h3 class="text-2xl font-semibold mb-6">Your Contacts</h3>

    <div class="flex justify-between items-center mb-4">
        <label for="sortOrder" class="text-gray-600 font-medium">Sort By:</label>
        <select id="sortOrder" class="px-4 py-2 bg-white border border-gray-300 rounded-md">
            <option value="asc">A-Z</option>
            <option value="desc">Z-A</option>
        </select>
    </div>

		<div class="overflow-x-auto" style="max-height: 400px; overflow-y: auto;">
		    <table class="min-w-full bg-white" id="contactsTable">
		        <thead class="bg-gray-200">
		            <tr >
		                <th class="py-3 px-5 text-left text-gray-600 font-medium">Profile</th>
		                <th class="py-3 px-5 text-left text-gray-600 font-medium">Name</th>
		                <th class="py-3 px-5 text-left text-gray-600 font-medium">Phone</th>
		                <th class="py-3 px-5 text-left text-gray-600 font-medium">Email</th>
		            </tr>
		        </thead>
		       <tbody id="contactsBody">
			    <%
			        dbUtil dbutil = new dbUtil();
			        List<contacts_db> cont = dbutil.getContacts(id);
			        if (!cont.isEmpty()) {
			            for (contacts_db c : cont) {
			                byte[] image_data = c.getProfilePhoto(); // Get the profile photo byte array
			                String base64Image = null;
			                if (image_data != null) {
			                    base64Image = Base64.getEncoder().encodeToString(image_data);
			                }
			             	request.setAttribute("d", c.getId());
			          
			    %>
			    <tr class="border-b hover:bg-gray-50 transition duration-200 cursor-pointer contact-row" data-contact-id="<%= c.getId() %>">
			        <td class="py-3 px-5">
			            <img src="<%= base64Image != null ? "data:image/jpeg;base64," + base64Image : "../img/default.jpg" %>" alt="Profile" class="contact-image">
			        </td>
			        <td class="py-3 px-5"><%= c.getName() %></td>
			        <td class="py-3 px-5"><%= c.getPhone() %></td>
			        <td class="py-3 px-5"><%= c.getEmail() %></td>
			    </tr>
			    <%
			            }
			        } else {
			    %>
			    <tr>
			        <td colspan="4" class="py-3 px-5 text-center text-gray-500">No Contacts Available</td>
			    </tr>
			    <%
			        }
			    %>
			</tbody>

		    </table>
		</div>


	<div id="addContactPopup" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50 hidden">
	    <div class="bg-white rounded-lg shadow-lg p-6 w-96">
	        <h2 class="text-2xl font-semibold mb-4">Add Contact</h2>
	        <form id="addContactForm" action="../contacts" method="post" enctype="multipart/form-data">
	            <div class="mb-4">
	                <label for="contactName" class="block text-sm font-medium text-gray-700">Name</label>
	                <input type="text" id="contactName" name="name" class="border border-gray-300 rounded-lg p-2 w-full" required>
	            </div>
	            <div class="mb-4">
	                <label for="contactPhone" class="block text-sm font-medium text-gray-700">Phone</label>
	                <input type="tel" id="contactPhone" name="phone" class="border border-gray-300 rounded-lg p-2 w-full" required>
	            </div>
	            <div class="mb-4">
	                <label for="contactEmail" class="block text-sm font-medium text-gray-700">Email</label>
	                <input type="email" id="contactEmail" name="email" class="border border-gray-300 rounded-lg p-2 w-full" required>
	            </div>
	            <div class="mb-4">
	                <label for="contactPhoto" class="block text-sm font-medium text-gray-700">Profile Photo</label>
	                <input type="file" id="contactPhoto" name="photo" accept="image/*" class="border border-gray-300 rounded-lg p-2 w-full">
	            </div>
	            <div class="flex justify-between">
	                <button type="button" id="closePopup" class="bg-gray-300 hover:bg-gray-400 text-black px-4 py-2 rounded-lg">Cancel</button>
	                <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg">Add Contact</button>
	            </div>
	        </form>
	    </div>
	</div>


            <script>
                const addContactPopup = document.getElementById('addContactPopup');
                const mainAddContactButton = document.getElementById('mainAddContactButton'); // Changed to main button ID
                const closePopupButton = document.getElementById('closePopup');

                mainAddContactButton.addEventListener('click', () => {
                    addContactPopup.classList.remove('hidden');
                });

                closePopupButton.addEventListener('click', () => {
                    addContactPopup.classList.add('hidden');
                });
            </script>
        </main>
    </div>
    
    <div id="contactModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 class="text-2xl font-semibold mb-4">Contact Details</h2>
            <div id="modalContent">
                <p><strong>Name:</strong> <span id="modalName"></span></p>
                <p><strong>Phone:</strong> <span id="modalPhone"></span></p>
                <p><strong>Email:</strong> <span id="modalEmail"></span></p>
            </div>
            <div class="mt-6">
                <button class="bg-yellow-400 text-white px-4 py-2 rounded-lg hover:bg-yellow-500 transition duration-300" id="editContactBtn">Edit</button>
				            <button class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition duration-300" id="deleteContactBtn">Delete</button>
            </div>
        </div>
    </div>

	<div id="editContactModal" class="modal">
	    <div class="modal-content">
	        <span class="close" id="closeEditModal">&times;</span>
	        <h2 class="text-2xl font-semibold mb-4">Edit Contact</h2>
	        
	        <!-- Form for editing contact -->
	        <form id="editContactForm" action = "../editContact" method = "post">
	            <div class="mb-4">
	                <label for="editName" class="block text-sm font-medium text-gray-700">Name</label>
	                <input type="text" id="editName" name="editName" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" required>
	            </div>
	            <div class="mb-4">
	                <label for="editPhone" class="block text-sm font-medium text-gray-700">Phone</label>
	                <input type="text" id="editPhone" name="editPhone" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" required>
	            </div>
	            <div class="mb-4">
	                <label for="editEmail" class="block text-sm font-medium text-gray-700">Email</label>
	                <input type="email" id="editEmail" name="editEmail" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" required>
	            </div>
	            <div class="mt-6">
	                <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600 transition duration-300" id="saveEditBtn">Save</button>
	                <button type="button" class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition duration-300" id="cancelEditBtn">Cancel</button>
	            </div>
	        </form>
	    </div>
	</div>

	<div id="importModal" class="fixed inset-0 flex items-center justify-center z-50 hidden bg-black bg-opacity-50">
	    <div class="bg-white rounded-lg shadow-lg p-6 w-96">
	        <h2 class="text-xl font-semibold mb-4">Import Contacts</h2>
	        <form action="../importContacts" method="post" enctype="multipart/form-data">
	            <div class="mb-4">
	                <label for="fileInput" class="block text-sm font-medium text-gray-700">Choose VCF file</label>
	                <input type="file" name="vcfFile" id="fileInput" accept=".vcf" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-500 focus:border-blue-500" required>
	            </div>
	            <div class="flex justify-end">
	                <button type="button" id="cancelBtn" class="mr-2 px-4 py-2 bg-gray-300 text-gray-700 rounded-md hover:bg-gray-400">Cancel</button>
	                <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700">Import</button>
	            </div>
	        </form>
	    </div>
	</div>


    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const contactModal = document.getElementById("contactModal");
            const editContactModal = document.getElementById("editContactModal");
            const closeContactModal = contactModal.querySelector(".close");
            const closeEditModal = document.getElementById("closeEditModal");
            const editContactBtn = document.getElementById("editContactBtn");
            const saveEditBtn = document.getElementById("saveEditBtn");
            const cancelEditBtn = document.getElementById("cancelEditBtn");

            const openContactModal = (name, phone, email) => {
                document.getElementById("modalName").innerText = name;
                document.getElementById("modalPhone").innerText = phone;
                document.getElementById("modalEmail").innerText = email;
                contactModal.style.display = "block"; 
            };

            closeContactModal.onclick = function() {
                contactModal.style.display = "none"; 
            };

            editContactBtn.onclick = function() {
                document.getElementById("editName").value = document.getElementById("modalName").innerText;
                document.getElementById("editPhone").value = document.getElementById("modalPhone").innerText;
                document.getElementById("editEmail").value = document.getElementById("modalEmail").innerText;

                contactModal.style.display = "none"; 
                editContactModal.style.display = "block"; 
            };

            saveEditBtn.onclick = function() {
                const updatedName = document.getElementById("editName").value;
                const updatedPhone = document.getElementById("editPhone").value;
                const updatedEmail = document.getElementById("editEmail").value;

                document.getElementById("modalName").innerText = updatedName;
                document.getElementById("modalPhone").innerText = updatedPhone;
                document.getElementById("modalEmail").innerText = updatedEmail;

                editContactModal.style.display = "none"; 
            };

            closeEditModal.onclick = function() {
                editContactModal.style.display = "none"; 
            };

            cancelEditBtn.onclick = function() {
                editContactModal.style.display = "none"; 
            };

            window.onclick = function(event) {
                if (event.target === contactModal) {
                    contactModal.style.display = "none"; 
                }
                if (event.target === editContactModal) {
                    editContactModal.style.display = "none"; 
                }
            };

        });

    function openModal() {
        document.getElementById('importModal').classList.remove('hidden');
    }

    function closeModal() {
        document.getElementById('importModal').classList.add('hidden');
    }

    document.getElementById('importBtn').addEventListener('click', function(event) {
        event.preventDefault(); 
        openModal(); 
    });

    document.getElementById('cancelBtn').addEventListener('click', closeModal);
</script>
    
   <script src="../js/script.js"></script>
</body>
</html>
