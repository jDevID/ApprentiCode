import { BaseEntity } from './base-entity.interface';
import { Resource } from './resource.interface';
import { Entry } from './entry.interface';
import { Project } from './project.interface';

export interface Complexity extends BaseEntity {
  name: string;
  resources: Resource[];
  entries: Entry[];
  project: Project;
}
