<template>
  <div>
    <h1>Form</h1>
    <form @submit.prevent="onSubmit">
      <input name="sei" v-model="formInput.sei"/>
      <br/>
      <input name="mei" v-model="formInput.mei"/>
      <br/>
      <input type="submit"/>
    </form>
  </div>
</template>

<script setup>
definePageMeta({layout: false,});

const formInput = ref({
  sei: '',
  mei: '',
})

const onSubmit = (e) => {
  console.log(formInput.value.sei, formInput.value.mei)
};
</script>