import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AddPostService } from './add-post.service';
import { PostPayload } from './model/post-payload.model';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm: FormGroup;
  postPayload: PostPayload;
  title = new FormControl('');
  body = new FormControl('');

  constructor(private addPostService: AddPostService, private router: Router) { 
    this.addPostForm = new FormGroup({
      title: this.title,
      body: this.body
    });

    this.postPayload = {
      id: '',
      title: '',
      content: '',
      username: ''
    }
  }

  ngOnInit(): void {
  }

  addPost() {
    this.postPayload.title = this.addPostForm.get('title').value;
    this.postPayload.content = this.addPostForm.get('body').value;
    this.addPostService.addPost(this.postPayload).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      console.log('Failure Resposne');
    });
  }

}
