// function show(){
//   console.log(this);
// }
// show();//nothing..


// const obj={
//   val:10,
//   show(){
//     console.log(this.val);
//   }
// };
// const fn=obj.show;
// fn();//undefined..
// obj.show();//10

// const fn1=obj.show.bind(obj);//binding creates connectipn bw this and obj
// fn1();//10

//explicit binding
function show(){
  console.log(this.val);
}
show.call({val:20});
show.apply({val:30});

//constructor binding
function Person(name){
  this.name=name;
}
const p=new Person('JS');
console.log(p.name);

// const obj={
//   val:10,
//   show:()=>console.log(this.val)
// }
// obj.show()//undefined

// const obj={
//   val:10,
//   show(){
//     return()=>console.log(this.val)
//   }
// }

// const fn=obj.show()
// fn()//10

//nested..

