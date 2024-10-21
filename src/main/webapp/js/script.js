document.addEventListener('DOMContentLoaded', () => {
    const contactModal = document.getElementById("contactModal");
    const editContactModal = document.getElementById("editContactModal");
    const closeContactModal = contactModal.querySelector(".close");
    const closeEditModal = document.getElementById("closeEditModal");
    const editContactBtn = document.getElementById("editContactBtn");
    const saveEditBtn = document.getElementById("saveEditBtn");
    const cancelEditBtn = document.getElementById("cancelEditBtn");
    
    var contactRows = document.getElementsByClassName("contact-row");

    for (var i = 0; i < contactRows.length; i++) {
        contactRows[i].onclick = function() {
            var name = this.cells[1].innerText; 
            var phone = this.cells[2].innerText; 
            var email = this.cells[3].innerText; 
            var contactId = this.getAttribute('data-contact-id'); 
            
            document.getElementById("modalName").innerText = name;
            document.getElementById("modalPhone").innerText = phone;
            document.getElementById("modalEmail").innerText = email;

            document.getElementById("deleteContactBtn").setAttribute('data-contact-id', contactId);
            
            contactModal.style.display = "block";
        }
    }

    closeContactModal.onclick = function() {
        contactModal.style.display = "none";
    };

    closeEditModal.onclick = function() {
        editContactModal.style.display = "none";
    };

    cancelEditBtn.onclick = function() {
        editContactModal.style.display = "none"; 
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

    document.getElementById("deleteContactBtn").onclick = function() {
        const contactId = this.getAttribute('data-contact-id'); 
        const confirmation = confirm("Are you sure you want to delete this contact?");
        
        if (confirmation) {
            window.location.href = "../deleteContact?id=" + contactId; 
        }
        
        contactModal.style.display = "none"; 
    };

    window.onclick = function(event) {
        if (event.target === contactModal) {
            contactModal.style.display = "none"; 
        }
        if (event.target === editContactModal) {
            editContactModal.style.display = "none"; 
        }
    };

    const sortOrderSelect = document.getElementById('sortOrder');
    const contactsBody = document.getElementById('contactsBody');

    function sortContacts(order) {
        const rows = Array.from(contactsBody.querySelectorAll('tr'));

        rows.sort((a, b) => {
            const nameA = a.cells[1].innerText.toLowerCase(); 
            const nameB = b.cells[1].innerText.toLowerCase();

            if (order === 'asc') {
                return nameA > nameB ? 1 : -1;
            } else if (order === 'desc') {
                return nameA < nameB ? 1 : -1;
            }
        });

        contactsBody.innerHTML = '';
        rows.forEach(row => contactsBody.appendChild(row));
    }

    sortOrderSelect.addEventListener('change', (e) => {
        const sortOrder = e.target.value;
        sortContacts(sortOrder);
    });

    sortContacts('asc'); 
});



