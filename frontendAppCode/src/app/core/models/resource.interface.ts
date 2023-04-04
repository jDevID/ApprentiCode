import { BaseEntity } from './base-entity.interface';
import { Complexity } from './complexity.interface';

export interface Resource extends BaseEntity {
  title: string;
  url: string;
  resourceType: string;
  complexity: Complexity;
}
