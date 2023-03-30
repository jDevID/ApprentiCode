import {Project} from "./project";

export class User {
  id: number;
  lastname: string;
  firstname: string;
  email: string;
  projects: List<Project>;
  role: string;
  active: boolean;
  dateCreated: Date;
  lastUpdated: Date;
}
