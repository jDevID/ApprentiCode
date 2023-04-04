import { BaseEntity } from './base-entity.interface';
import { Entry } from './entry.interface';

export interface Tag extends BaseEntity {
  name: string;
  os: string;
  description: string;
  entries: Entry[];
}
