import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {
  transform(items: any[], searchTerm: string, searchFields: string[] = []): any[] {
    if (!items || !searchTerm) {
      return items;
    }

    return items.filter(item => {
      for (const field of searchFields) {
        if (item[field] && item[field].toString().toLowerCase().includes(searchTerm.toLowerCase())) {
          return true;
        }
      }
      return false;
    });
  }
}
