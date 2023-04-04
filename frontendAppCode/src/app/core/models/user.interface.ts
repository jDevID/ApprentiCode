import { BaseEntity } from './base-entity.interface';
import { Role } from './role.interface';
import { Project } from './project.interface';

export interface User extends BaseEntity {
  lastname: string;
  firstname: string;
  email: string;
  password: string;
  role: Role;
  projects: Project[];
}
