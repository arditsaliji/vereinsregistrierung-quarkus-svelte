<style>
    .NavbarItems {
        background: #1f6d80;
        height: 60px;
        display: flex;
        align-items: center;
        font-size: 1.2rem;
        margin-top: -10px;
        margin-left: -10px;
        margin-right: -10px;
    }

    .nav-menu {
        display: grid;
        grid-template-columns: repeat(5, auto);
        grid-gap: 10px;
        list-style: none;
        text-align: center;
        justify-content: end;
        margin-right: 2rem;
    }

    a {
        color: #e5e5e7 !important;
        text-decoration: none !important;
        padding: 0.5rem 1rem !important;
    }

    a:hover {
        color: #fff !important;
        border-radius: 4px !important;
        transition: all 0.2s ease-out !important;
    }
</style>
<main>
    <nav class="NavbarItems">
        <ul class="nav-menu">
            <Link to="/" class="nav-links" style="color: #e5e5e7; text-decoration: none; padding: 0.5rem 1rem;">
                Login
            </Link>
            <Link to="/verein" class="nav-links" style="color: #e5e5e7; text-decoration: none; padding: 0.5rem 1rem;">
                Vereine
            </Link>
            <Link to="/mitglied" class="nav-links" style="color: #e5e5e7; text-decoration: none; padding: 0.5rem 1rem;">
                Mitglieder
            </Link>
        </ul>
    </nav>
</main>

<script>
    import {Link} from "svelte-routing";
</script>
