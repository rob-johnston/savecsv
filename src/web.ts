import { WebPlugin } from '@capacitor/core';

import type { SaveCSVPlugin } from './definitions';

export class SaveCSVWeb extends WebPlugin implements SaveCSVPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
