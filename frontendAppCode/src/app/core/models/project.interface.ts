import { BaseEntity } from './base-entity.interface';
import { Complexity } from './complexity.interface';
import { User } from './user.interface';

export interface Project extends BaseEntity {
  name: string;
  description: string;
  complexities: Complexity[];
  user: User;
}
