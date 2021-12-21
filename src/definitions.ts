export interface SaveCSVPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
