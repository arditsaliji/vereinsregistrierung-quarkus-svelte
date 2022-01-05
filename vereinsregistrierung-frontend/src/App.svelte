<style>
	:global(button) {
		border-radius: 24px;
		font-size: 22px;
		border: none;
		background: #ffffff;
		margin-right: 5px;
	}

	:global(button:hover) {
		background: #c4c4c4;
		cursor: pointer;
	}
</style>
<main>
	<Router url="{url}">
		<Navbar />
		<div>
			<Route path="/" component="{Login}" />
			<Route path="/verein" component="{Vereine}" />
			<Route path="/mitglied" component="{Mitglieder}" />
		</div>
	</Router>
</main>

<script>
	import {Router, Route} from "svelte-routing";
	import Login from "./pages/Login.svelte";
	import Vereine from "./pages/Vereine.svelte";
	import Mitglieder from "./pages/Mitglieder.svelte";
	import Navbar from "./components/Navbar.svelte";

	export let url = "";
</script>
