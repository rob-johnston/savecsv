export interface SaveCSVPlugin {
  writeCSVToStorage(options: { value: string }): Promise<{ value: string }>;
}
