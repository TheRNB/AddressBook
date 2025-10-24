document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('createForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        fetch('/addressbook/newbook', { method: 'POST' })
            .then((r) => r.json())
            .then((data) => {
                const container = document.getElementById('addressBookContainer');
                let list = document.getElementById('addressBookList');
                const noMsg = document.getElementById('noBooksMsg');

                if (!list) {
                    list = document.createElement('ul');
                    list.id = 'addressBookList';
                    if (noMsg) container.replaceChild(list, noMsg);
                    else container.appendChild(list);
                }

                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = `/view/addressbook/${data.id}/view`;
                a.textContent = `Address Book ${data.id}`;
                li.appendChild(a);
                list.appendChild(li);
            });
    });
});
