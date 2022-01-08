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

        <div style="margin-top: 50px; margin-bottom: 30px; text-align: center;">
                <input type="text" bind:value={filterString}/>
                <IconButton class="material-icons" on:click={() => filterMitglieder()}>search</IconButton>
        </div>

        <p class="title">Mitglieder ({mitglieder.length})</p>

        <table class="data-table">
            <tr>
                <th>Vorname</th>
                <th>Nachname</th>
                <th>Adresse</th>
                <th>Geburtsdatum</th>
                <th>Verein</th>
                <th> </th>
            </tr>
            {#each mitglieder as mitglied}
                <tr>
                    <td>{mitglied.vorname}</td>
                    <td>{mitglied.nachname}</td>
                    <td>{mitglied.adresse}</td>
                    <td>{mitglied.geburtsdatum}</td>
                    <td>{mitglied.verein.name}</td>
                    <td>
                        <IconButton class="material-icons" on:click={() => openUpdateDialog(mitglied)}>edit</IconButton>
                        <IconButton class="material-icons" on:click={() => deleteMitglied(mitglied.id)}>delete</IconButton>
                    </td>
                </tr>
            {/each}
        </table>

        <Dialog bind:active={editDialogActive} style="background: #fff">
            <p style="font-weight: bold;">{selectedMitglied.vorname} {selectedMitglied.nachname} bearbeiten</p>
            <label>
                Vorname:
                <input type="text" bind:value={selectedMitglied.vorname}/>
            </label>
            <label>
                Nachname:
                <input type="text" bind:value={selectedMitglied.nachname}/>
            </label>
            <label>
                Adresse:
                <input type="text" bind:value={selectedMitglied.adresse}/>
            </label>
            <label>
                Geburtsdatum:
                <input type="text" bind:value={selectedMitglied.geburtsdatum}/>
            </label>
            <label>
                Verein:
                <select bind:value={selectedMitglied.verein}>
                    {#each vereine as verein}
                        <option value={verein}>
                            {verein.name}
                        </option>
                    {/each}
                </select>
            </label>

            <div>
                <button on:click={() => closeDialog()}>Abbrechen</button>
                <button on:click={() => saveMitglied(selectedMitglied)} style="margin-left: 300px;">Speichern</button>
            </div>
        </Dialog>

        <Dialog bind:active={addDialogActive} style="background: #fff">
            <p style="font-weight: bold;">Neues Mitglied erstellen</p>
            <label>
                Vorname:
                <input type="text" bind:value={newMitglied.vorname}/>
            </label>
            <label>
                Nachname:
                <input type="text" bind:value={newMitglied.nachname}/>
            </label>
            <label>
                Adresse:
                <input type="text" bind:value={newMitglied.adresse}/>
            </label>
            <label>
                Geburtsdatum:
                <input type="text" bind:value={newMitglied.geburtsdatum}/>
            </label>
            <label>
                Verein:
                <select bind:value={newMitglied.verein}>
                    {#each vereine as verein}
                        <option value={verein}>
                            {verein.name}
                        </option>
                    {/each}
                </select>
            </label>

            <div>
                <button on:click={() => closeDialog()}>Abbrechen</button>
                <button on:click={() => saveNewMitglied(newMitglied)} style="margin-left: 300px;">Speichern</button>
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

    let mitglieder = [];
    let vereine = [];
    let filterString = "";
    let editDialogActive;
    let addDialogActive;
    let selectedMitglied;
    let newMitglied = {
        vorname: "",
        nachname: "",
        adresse: "",
        geburtsdatum: ""
    };
    onMount(async function() {
        const response = await fetch("/mitglieder");
        mitglieder = await response.json();
        const responseVereine = await fetch("/vereine");
        vereine = await responseVereine.json();
    });

    async function filterMitglieder() {
        const response = await fetch("/mitglieder/filtered/" + filterString);
        mitglieder = await response.json();
    }

    async function deleteMitglied(mitgliedId) {
        const response = await fetch("/mitglieder/delete/" + mitgliedId, { method: 'DELETE'});
        mitglieder = mitglieder.filter(function(mitglied) { return mitglied.id !== mitgliedId });
    }

    function closeDialog() {
        editDialogActive = false;
        addDialogActive = false;
        newMitglied = {
            vorname: "",
            nachname: "",
            adresse: "",
            geburtsdatum: ""
        };
    }

    function openUpdateDialog(mitglied) {
        selectedMitglied = mitglied;
        editDialogActive = true;
    }

    async function addMitglied(newMitglied) {
        newMitglied.id = Math.floor(Math.random() * 100);
        const mitgliedAsJson = '{"id": ' + newMitglied.id + ', "vorname": "' + newMitglied.vorname + '", "nachname": "' + newMitglied.nachname + '", "adresse": "' + newMitglied.adresse + '", "geburtsdatum": "' + newMitglied.geburtsdatum + '", "verein": ' + '{"id": ' + newMitglied.verein.id + ', "name": "' + newMitglied.verein.name + '", "kategorie": "' + newMitglied.verein.kategorie + '", "zweck": "' + newMitglied.verein.zweck + '", "entstehung": "' + newMitglied.verein.entstehung + '", "adresse": "' + newMitglied.verein.adresse + '", "obmann": "' + newMitglied.verein.obmann + '"}' + '}';
        console.log(mitgliedAsJson);
        const response = await fetch("/mitglieder/add", { method: 'POST', headers: {'Content-Type': 'application/json'}, body: mitgliedAsJson});
        mitglieder = [...mitglieder, newMitglied];
        newMitglied = {
            vorname: "",
            nachname: "",
            adresse: "",
            geburtsdatum: ""
        };
    }

    async function saveNewMitglied(newMitglied) {
        await addMitglied(newMitglied);
        newMitglied = {
            vorname: "",
            nachname: "",
            adresse: "",
            geburtsdatum: ""
        };
        addDialogActive = false;
    }

    async function saveMitglied(updatedMitglied) {
        await updateMitglied(updatedMitglied);
        editDialogActive = false;
    }

    async function updateMitglied(updatedMitglied) {
        const mitgliedAsJson = '{"id": ' + updatedMitglied.id + ', "vorname": "' + updatedMitglied.vorname + '", "nachname": "' + updatedMitglied.nachname + '", "adresse": "' + updatedMitglied.adresse + '", "geburtsdatum": "' + updatedMitglied.geburtsdatum + '", "verein": ' + '{"id": ' + updatedMitglied.verein.id + ', "name": "' + updatedMitglied.verein.name + '", "kategorie": "' + updatedMitglied.verein.kategorie + '", "zweck": "' + updatedMitglied.verein.zweck + '", "entstehung": "' + updatedMitglied.verein.entstehung + '", "adresse": "' + updatedMitglied.verein.adresse + '", "obmann": "' + updatedMitglied.verein.obmann + '"}' + '}';
        console.log(mitgliedAsJson);
        const response = await fetch("/mitglieder/edit", { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: mitgliedAsJson});
        var foundIndex = mitglieder.findIndex(mitglied => mitglied.id === updatedMitglied.id);
        mitglieder[foundIndex] = updatedMitglied;
    }

    function openAddDialog() {
        addDialogActive = true;
    }
</script>
