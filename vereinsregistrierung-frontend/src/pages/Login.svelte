<style>
    :global(.link-button) {
        background: #1f6d80;
        border-radius: 24px;
        padding: 10px;
        color: #fff !important;
        font-weight: bold;
        margin-top: 10px;
        margin-left: 60px;
        text-decoration: none !important;
    }

    :global(.link-button:hover) {
        background: #144854;
    }
</style>
<main>
    <div style="text-align: center; margin-top: 100px">
        <label>
            Username:
            <input />
        </label>
        <label style="margin-bottom: 10px;">
            Password:
            <input type="password"/>
        </label>
        <Link to="/verein"
              class="link-button">
            Anmelden
        </Link>
    </div>
</main>

<script>
    import {Link} from "svelte-routing";
</script>
