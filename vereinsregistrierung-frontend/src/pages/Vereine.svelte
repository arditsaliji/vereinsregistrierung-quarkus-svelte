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
                        <IconButton class="material-icons" on:click={() => openUpdateDialog(verein)}>edit</IconButton>
                        <IconButton class="material-icons" on:click={() => deleteVerein(verein.id)}>delete</IconButton>
                    </td>
                </tr>
            {/each}
        </table>

        <Dialog bind:active={editDialogActive} style="background: #fff">
            <p style="font-weight: bold;">{selectedVerein.name} bearbeiten</p>
            <label>
                Name:
                <input type="text" bind:value={selectedVerein.name}/>
            </label>
            <label>
                Kategorie:
                <input type="text" bind:value={selectedVerein.kategorie}/>
            </label>
            <label>
                Zweck:
                <input type="text" bind:value={selectedVerein.zweck}/>
            </label>
            <label>
                Entstehung:
                <input type="text" bind:value={selectedVerein.entstehung}/>
            </label>
            <label>
                Adresse:
                <input type="text" bind:value={selectedVerein.adresse}/>
            </label>
            <label>
                Obmann:
                <input type="text" bind:value={selectedVerein.obmann}/>
            </label>

            <div>
                <button on:click={() => closeDialog()}>Abbrechen</button>
                <button on:click={() => saveVerein(selectedVerein)} style="margin-left: 300px;">Speichern</button>
            </div>
        </Dialog>

        <Dialog bind:active={addDialogActive} style="background: #fff">
            <p style="font-weight: bold;">Neuen Verein erstellen</p>
            <label>
                Name:
                <input type="text" bind:value={newVerein.name}/>
            </label>
            <label>
                Kategorie:
                <input type="text" bind:value={newVerein.kategorie}/>
            </label>
            <label>
                Zweck:
                <input type="text" bind:value={newVerein.zweck}/>
            </label>
            <label>
                Entstehung:
                <input type="text" bind:value={newVerein.entstehung}/>
            </label>
            <label>
                Adresse:
                <input type="text" bind:value={newVerein.adresse}/>
            </label>
            <label>
                Obmann:
                <input type="text" bind:value={newVerein.obmann}/>
            </label>

            <div>
                <button on:click={() => closeDialog()}>Abbrechen</button>
                <button on:click={() => saveNewVerein(newVerein)} style="margin-left: 300px;">Speichern</button>
            </div>
        </Dialog>

        <div style="text-align: right; margin-top: 20px; margin-right: 50px;">
            <IconButton class="material-icons" on:click={() => openAddDialog()}>add</IconButton>
        </div>

    </div>
</main>

<script>
    import {onMount} from "svelte";
    import IconButton from '@smui/icon-button';
    import { Dialog } from 'svelte-materialify'

    let vereine = [];
    let selectedVerein;
    let newVerein = {
        name: "",
        kategorie: "",
        zweck: "",
        entstehung: "",
        adresse: "",
        obmann: ""
    };
    let editDialogActive;
    let addDialogActive;
    onMount(async function() {
        const response = await fetch("/vereine");
        vereine = await response.json();
    });

    async function updateVerein(updatedVerein) {
        const vereinAsJson = '{"id": ' + updatedVerein.id + ', "name": "' + updatedVerein.name + '", "kategorie": "' + updatedVerein.kategorie + '", "zweck": "' + updatedVerein.zweck + '", "entstehung": "' + updatedVerein.entstehung + '", "adresse": "' + updatedVerein.adresse + '", "obmann": "' + updatedVerein.obmann + '"}';
        console.log(vereinAsJson);
        const response = await fetch("/vereine/edit", { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: vereinAsJson});
        var foundIndex = vereine.findIndex(verein => verein.id === updatedVerein.id);
        vereine[foundIndex] = updatedVerein;
    }

    async function deleteVerein(vereinId) {
        const response = await fetch("/vereine/delete/" + vereinId, { method: 'DELETE'});
        vereine = vereine.filter(function(verein) { return verein.id !== vereinId });
    }

    async function addVerein(newVerein) {
        newVerein.id = Math.floor(Math.random() * 100);
        const vereinAsJson = '{"id": ' + newVerein.id + ', "name": "' + newVerein.name + '", "kategorie": "' + newVerein.kategorie + '", "zweck": "' + newVerein.zweck + '", "entstehung": "' + newVerein.entstehung + '", "adresse": "' + newVerein.adresse + '", "obmann": "' + newVerein.obmann + '"}';
        console.log(vereinAsJson);
        const response = await fetch("/vereine/add", { method: 'POST', headers: {'Content-Type': 'application/json'}, body: vereinAsJson});
        vereine = [...vereine, newVerein];
        newVerein = {
            name: "",
            kategorie: "",
            zweck: "",
            entstehung: "",
            adresse: "",
            obmann: ""
        };
    }

    function openUpdateDialog(verein) {
        selectedVerein = verein;
        editDialogActive = true;
    }

    function openAddDialog() {
        addDialogActive = true;
    }

    function closeDialog() {
        editDialogActive = false;
        addDialogActive = false;
        newVerein = {
            name: "",
            kategorie: "",
            zweck: "",
            entstehung: "",
            adresse: "",
            obmann: ""
        };
    }

    async function saveVerein(updatedVerein) {
        await updateVerein(updatedVerein);
        editDialogActive = false;
    }

    async function saveNewVerein(newVerein) {
        await addVerein(newVerein);
        newVerein = {
            name: "",
            kategorie: "",
            zweck: "",
            entstehung: "",
            adresse: "",
            obmann: ""
        };
        addDialogActive = false;
    }


</script>
