<template>
  <div>
    <h3>LoginForm</h3>
    <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
      <Field name="email" />
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
import { object, string } from 'yup';
import { ref } from 'vue';

const submitting = ref(false);

const schema = object({
  email: string()
    .required("Email is required")
    .email("Invalid email"),
  password: string()
    .required("Password is required")
    .min(8, "Password must be at least 8 characters")
    .max(16, "Password must not exceed 16 characters"),
});

const onSubmit = async () => {
  submitting.value = true;
  // 2秒待つ
  await new Promise((resolve) => setTimeout(resolve, 2000));
  console.log("submit");
  submitting.value = false;
};
</script>
