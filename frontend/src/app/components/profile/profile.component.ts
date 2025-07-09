import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  user: any;
  constructor(private router: Router) {}
  ngOnInit(): void {
    const stored = localStorage.getItem('user');
    if (stored) {
      this.user = JSON.parse(stored);
    }
  }

  goBack(): void {
    this.router.navigate(['/bookstore']); // or '/bookstore' if that's your book list path
  }
}
