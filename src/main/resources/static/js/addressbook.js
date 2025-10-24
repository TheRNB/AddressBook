document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addBuddyForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const fd = new FormData(form);
        const payload = {
            name: fd.get('name'),
            phone: fd.get('phone'),
            address: fd.get('address')
        };

        fetch(`/addressbook/${ADDRESSBOOK_ID}/add`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
            .then((r) => r.json())
            .then((data) => {
                const list = document.getElementById('buddyList');
                list.innerHTML = '';

                data.buddies.forEach((b) => {
                    const li = document.createElement('li');
                    li.innerHTML = `<strong>Name:</strong> ${b.name}, <strong>Phone:</strong> ${b.phone}, <strong>Address:</strong> ${b.address}`;
                    list.appendChild(li);
                });

                if (data.buddies.length === 0) {
                    const li = document.createElement('li');
                    li.id = 'noBuddyMsg';
                    li.textContent = '*(No buddies in this address book)*';
                    list.appendChild(li);
                }

                form.reset();
            });
    });
});
