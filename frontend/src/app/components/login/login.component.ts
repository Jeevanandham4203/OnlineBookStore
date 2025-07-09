import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  login(): void {
    this.http.post<any>(`http://localhost:8085/api/users/login?email=${this.email}&password=${this.password}`, {})
      .subscribe({
        next: (user) => {
          localStorage.setItem('user',JSON.stringify(user));
          alert(`Login successful! Welcome ${user.name}`);
          this.router.navigate(['bookstore']); 
        },
        error: (err) => {
          console.error('Login error:', err);
          this.errorMessage = 'Invalid email or password. Please register if you are new.';
        }
      });
  }
}
