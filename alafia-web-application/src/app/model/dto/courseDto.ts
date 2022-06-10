export class CourseDto {
  courseId: string;
  orderId: string;
  clientId: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;

  constructor(courseId: string,
              orderId: string,
              clientId: string,
              bookingId: string,
              dinnerTableId: string,
              restaurantId: string) {
    this.courseId = courseId;
    this.orderId = orderId;
    this.clientId = clientId;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
  }
}
