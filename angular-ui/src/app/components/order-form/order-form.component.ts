import { Component, OnInit } from '@angular/core';
import { OrderEventsService, StockUpdate } from '../../services/order-events.service';
import { OrderService } from '../../services/order.service';

interface Order {
  id: number;
  product: string;
  quantity: number;
  remainingStock?: number;
  message?: string;
}

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {

  product = '';
  quantity = 1;

  // Keep a list of all orders
  orders: Order[] = [];

  constructor(
    private orderService: OrderService,
    private orderEventsService: OrderEventsService
  ) {}

  ngOnInit(): void {
    // Listen to stock updates
    this.orderEventsService.listenToStockUpdates().subscribe((data: StockUpdate) => {
        console.log('Received stock update:', data);
      const order = this.orders.find(o => o.id === data.orderId);
      if (order) {
        order.remainingStock = data.stock;
        order.message = `Order ${data.orderId} processed. Remaining stock: ${data.stock}`;
      }
    });
  }

  submitOrder() {
    this.orderService.createOrder({
      product: this.product,
      quantity: this.quantity
    }).subscribe({
      next: (res: any) => {
        // Add to local orders list
        this.orders.push({
          id: res.id,
          product: res.product,
          quantity: res.quantity,
          remainingStock: undefined,
          message: 'Order created. Waiting for stock update...'
        });

        // Reset form
        this.product = '';
        this.quantity = 1;
      },
      error: () => {
        alert('Error creating order');
      }
    });
  }
}
