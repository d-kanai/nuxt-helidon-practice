<template>
  <div>
    <h3>LoginForm</h3>
    <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
      <Field name="email" rules="required|email" />
      <span>{{ errors.email }}</span>
      <br />
      <Field name="password" type="password" />
      <span>{{ errors.password }}</span>
      <br />
      <button :disabled="submitting">
        {{ submitting ? "is submitting..." : "Submit" }}
      </button>
    </Form>
  </div>
</template>

<script setup>
import { Form, Field } from "vee-validate";
const schema = {
  email: "required|email",
  password: "required|minLength:8|maxLength:16",
};

const submitting = ref(false);
const onSubmit = async () => {
  submitting.value = true;
  // 2秒待つ
  await new Promise((resolve) => setTimeout(resolve, 2000));
  console.log("submit");
  submitting.value = false;
};
</script>
