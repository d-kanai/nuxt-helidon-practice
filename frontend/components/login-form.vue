<template>
  <div>
    <h3>LoginForm</h3>
    <form @submit.prevent="onSubmit">
      <input name="email" v-model="formInput.email"/>
      <br/>
      <input name="password" v-model="formInput.password"/>
      <br/>
      <input type="submit"/>
    </form>
  </div>
</template>

<script setup>
import {z} from 'zod'

const loginFormSchema = z.object({
  email: z.string().email(),
  password: z.string().min(8).max(16)
})

const formInput = ref({
  email: '',
  password: '',
})

const onSubmit = (e) => {
  loginFormSchema.parse(formInput.value)
  console.log(formInput.value)
};
</script>