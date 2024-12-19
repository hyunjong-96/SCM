<script setup>
import {useRouter} from 'vue-router'

const router = useRouter();

console.log('redirect : ',router)


router.push('/dashboard')

</script>