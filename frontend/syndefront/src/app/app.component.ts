import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  koodi="";
  sent="";
  image="";

  constructor(private http:HttpClient) {}

  async onClickMe() {
    this.sent = this.koodi;
    //this.http.get('http://localhost:28083/greeting?hint=' + this.koodi).subscribe(data => {console.log(data); this.sent = data['id']}
    //);
    this.http.get('http://192.168.0.178:28083/get-image?hint='+this.koodi, {responseType: 'blob'}).subscribe(data => {
      const reader = new FileReader();
      reader.onload = (e : Event) => this.image = reader.result;
      reader.readAsDataURL(new Blob([data]));
      //console.log(data); this.sent = data['id']
      });
  }
}
