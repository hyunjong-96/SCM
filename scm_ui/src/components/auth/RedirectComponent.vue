<script setup>
import {useRouter} from 'vue-router'
import { useCookies } from 'vue3-cookies';

const router = useRouter();
const { cookies } = useCookies();

console.log('redirect : ',router)

const query = router.currentRoute.value.query

const accessToken = query["scm-token"]

console.log('redirect : ',accessToken)

cookies.set("scm-token", accessToken);

router.push('/dashboard')

</script>