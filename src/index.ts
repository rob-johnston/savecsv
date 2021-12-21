import { registerPlugin } from '@capacitor/core';

import type { SaveCSVPlugin } from './definitions';

const SaveCSV = registerPlugin<SaveCSVPlugin>('SaveCSV', {
  web: () => import('./web').then(m => new m.SaveCSVWeb()),
});

export * from './definitions';
export { SaveCSV };
