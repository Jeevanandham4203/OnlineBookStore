import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  user = {
    name: '',
    email: '',
    password: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  register(): void {
    this.http.post<any>('http://localhost:8085/api/users/register', this.user)
      .subscribe({
        next: (res) => {
          alert(res.message);
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error('Registration failed', err);
          alert('Registration failed. Try again.');
        }
      });
  }
}
