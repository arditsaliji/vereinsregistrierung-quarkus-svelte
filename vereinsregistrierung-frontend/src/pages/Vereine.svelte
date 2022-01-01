<style>

</style>
<main>
    <div class="container">
        <h1 class="app-title">Warenkorb</h1>

        <table>
            <tr>
                <th>Name</th>
                <th>Adresse</th>
                <th>Kategorie</th>
                <th>Zweck</th>
                <th>Obmann</th>
                <th>Entstehung</th>
                <th> </th>
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
                        <button>Editieren</button>
                    </td>
                    <td>
                        <button on:click={() => deleteItem(verein)}>Löschen</button>
                    </td>
                </tr>
            {/each}

            <div>
                {#if addItemModeActive}
                    <label>
                        Name: <input bind:value="{newItem.name}" />
                    </label>
                    <label>
                        Name: <input type="number" bind:value="{newItem.price}" />
                    </label>
                    <div>
                        <button on:click={() => addItemToList()}>Hinzufügen</button>
                    </div>
                {/if}
                {#if !addItemModeActive}
                    <button on:click={() => addItemModeActive = !addItemModeActive}>+</button>
                {/if}
            </div>
        </table>

    </div>
</main>

<script>
    import {onMount} from "svelte";

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
