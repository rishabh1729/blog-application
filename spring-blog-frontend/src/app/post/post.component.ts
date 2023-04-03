import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AddPostComponent } from '../add-post/add-post.component';
import { AddPostService } from '../add-post/add-post.service';
import { PostPayload } from '../add-post/model/post-payload.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  id: number;
  post: PostPayload = {
    id: '',
    content: '',
    title: '',
    username: ''
  };

  constructor(private route: ActivatedRoute, private postService: AddPostService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.postService.getPost(this.id).subscribe(data => this.post = data, error => console.log('Failure Response'));
  }

}
