import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatDate'
})
export class DatePipe implements PipeTransform {
  transform(value: Date | string, format: string = 'short'): string {
    const date = new Date(value);
    const dateFormatter = new Intl.DateTimeFormat('fr-FR', {
      year: format === 'short' ? '2-digit' : 'numeric',
      month: '2-digit',
      day: '2-digit',
    });
    return dateFormatter.format(date);
  }
}
