<style>
    .data-table {
        width: 100%;
        text-align: center;
    }

    .title {
        margin-left: 20px;
        margin-top: 50px;
        margin-bottom: 50px;
        font-size: 2.4rem;
        opacity: 70%;
    }

</style>
<main>
    <div class="container">
        <p class="title">Vereine ({vereine.length})</p>

        <table class="data-table">
            <tr>
                <th>Name</th>
                <th>Adresse</th>
                <th>Kategorie</th>
                <th>Zweck</th>
                <th>Obmann</th>
                <th>Entstehung</th>
                <th> </th>
            </tr>
            {#each vereine as verein}
                <tr>
                    <td>{verein.name}</td>
                    <td>{verein.adresse}</td>
                    <td>{verein.kategorie}</td>
                    <td>{verein.zweck}</td>
                    <td>{verein.obmann}</td>
                    <td>{verein.entstehung}</td>
                    <td>
                        <IconButton class="material-icons">edit</IconButton>
                        <IconButton class="material-icons">delete</IconButton>
                    </td>
                </tr>
            {/each}
        </table>

    </div>
</main>

<script>
    import {onMount} from "svelte";
    import IconButton from '@smui/icon-button';

    let vereine = [];
    let newItem = {
        "id": 100,
        "name": "",
        "price": 0
    };
    let addItemModeActive = false;
    onMount(async function() {
        const response = await fetch("/vereine");
        vereine = await response.json();
    });

    async function addItemToList() {
        const itemAsJson = '{"id": ' + newItem.id + ', "name": "' + newItem.name + '", "price": ' + newItem.price + '}';
        const response = await fetch("/items/addItem", { method: 'POST', headers: {'Content-Type': 'application/json'}, body: itemAsJson});
        const item = await response.json();
        vereine = [...vereine, item];
        newItem = {
            "id": 101,
            "name": "",
            "price": 0
        }
        addItemModeActive = false;
    }

    async function updateItem(updatedItem) {
        const itemAsJson = '{"id": ' + updatedItem.id + ', "name": "' + updatedItem.name + '", "price": ' + updatedItem.price + '}';
        const response = await fetch("/items/editItem", { method: 'POST', headers: {'Content-Type': 'application/json'}, body: itemAsJson});
        const item = await response.json();
        vereine = [...vereine, item];
    }

    async function deleteItem(deletedItem) {
        console.log(deletedItem);
        const itemAsJson = '{"id": ' + deletedItem.id + ', "name": "' + deletedItem.name + '", "price": ' + deletedItem.price + '}';
        const response = await fetch("/items/deleteItem", { method: 'POST', headers: {'Content-Type': 'application/json'}, body: itemAsJson});
        const response2 = await fetch("/items");
        console.log(response2);
        vereine = await response2.json();
    }
</script>
