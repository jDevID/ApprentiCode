import { BaseEntity } from './base-entity.interface';
import { Complexity } from './complexity.interface';
import { Tag } from './tag.interface';

export interface Entry extends BaseEntity {
  command: string;
  response: string;
  hint: string;
  complexity: Complexity;
  tags: Tag[];
}
