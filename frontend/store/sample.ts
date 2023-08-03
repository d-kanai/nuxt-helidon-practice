import { defineStore } from 'pinia';

type SampleState = {
  text: string;
  count: number;
};

export const useSampleStore = defineStore({
  id: 'sample',
  state: (): SampleState => ({
    text: 'This is data from the store',
    count: 0,
  }),
});
