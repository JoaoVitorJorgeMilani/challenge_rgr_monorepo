import { Component } from '@angular/core';
import { faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  faUser = faUser;
}
