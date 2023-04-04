import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'fileSize',
})
export class FileSizePipe implements PipeTransform {
  transform(sizeInBytes: number, ...args: unknown[]): string {
    if (!sizeInBytes) {
      return '0 Bytes';
    }

    const units = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const index = Math.floor(Math.log(sizeInBytes) / Math.log(1024));
    return `${parseFloat((sizeInBytes / Math.pow(1024, index)).toFixed(2))} ${units[index]}`;
  }
}
