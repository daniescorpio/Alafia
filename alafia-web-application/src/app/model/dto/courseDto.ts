export class CourseDto {
  courseId: string;
  orderId: string;
  clientId: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;
  urlVideo: string;

  constructor(courseId: string,
              orderId: string,
              clientId: string,
              bookingId: string,
              dinnerTableId: string,
              restaurantId: string,
              urlVideo: string) {
    this.courseId = courseId;
    this.orderId = orderId;
    this.clientId = clientId;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
    this.urlVideo = urlVideo;
  }
}
