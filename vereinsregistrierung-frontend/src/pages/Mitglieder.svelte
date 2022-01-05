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
    onMount(async function() {
        const response = await fetch("/mitglieder");
        mitglieder = await response.json();
    });
</script>
