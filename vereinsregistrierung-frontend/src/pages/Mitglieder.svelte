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

    let mitglieder = [];
    let filterString = "";
    onMount(async function() {
        const response = await fetch("/mitglieder");
        mitglieder = await response.json();
    });

    async function filterMitglieder() {
        const response = await fetch("/mitglieder/filtered/" + filterString);
        mitglieder = await response.json();
    }
</script>
