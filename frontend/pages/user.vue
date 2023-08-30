<template>
  <div>
    <h3>Register User</h3>
    <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
      <label for="name">Name:</label>
      <Field id="name" name="name" />
      <span>{{ errors.name }}</span>
      <br />
      <label for="age">Age:</label>
      <Field id="age" name="age" />
      <span>{{ errors.age }}</span>
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
  name: string()
    .required("Name is required"),
  age: string()
    .required("Age is required"),
});

const onSubmit = async () => {
  submitting.value = true;
  // 2秒待つ
  await new Promise((resolve) => setTimeout(resolve, 2000));
  console.log("submit");
  submitting.value = false;
};
</script>
