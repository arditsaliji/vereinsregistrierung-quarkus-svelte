<style>

</style>
<main>
	<Router url="{url}">
		<Navbar />
		<div>
			<Route path="/" component="{Login}" />
			<Route path="/vereine" component="{Vereine}" />
		</div>
	</Router>
</main>

<script>
	import {Router, Route, Link} from "svelte-routing";
	import Login from "./pages/Login.svelte";
	import Vereine from "./pages/Vereine.svelte";
	import Navbar from "./components/Navbar.svelte";

	export let url = "";
</script>
