import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';

export interface StockUpdate {
  orderId: number;
  product: string;
  stock: number;
}

@Injectable({
  providedIn: 'root'
})
export class OrderEventsService {

  constructor(private zone: NgZone) {}

  listenToStockUpdates(): Observable<StockUpdate> {
    return new Observable(observer => {
      const eventSource = new EventSource('http://localhost:8080/order-events');

      eventSource.addEventListener('stock-update', (event: any) => {
        this.zone.run(() => {
          observer.next(JSON.parse(event.data));
        });
      });

      eventSource.onerror = (err) => {
        eventSource.close();
        observer.error(err);
      };
    });
  }
}
