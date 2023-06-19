use corejava_demoapp_bharath;


create table users (
	user_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_name varchar(255) NOT NULL,
	email_id varchar(320),
	additional_info varchar(255),
	password varchar(255)
);


create table tasks (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    task_name varchar(500) NOT NULL,
	task_status enum('PENDING','STARTED', 'COMPLETED', 'INPROGRESS','BLOCKED'),
    task_priority enum('HIGH', 'MEDIUM', 'LOW'),
    createDate date,
    estdHrs numeric
    
);

INSERT INTO users (user_name, email_id, additional_info, password) VALUES ("bharath_sound","bharathwaj.soundararajan@gmail.com","Instructor FSSA", "password007");

INSERT INTO tasks(task_name, task_status, task_priority, createDate, estdHrs) 
VALUES ("Adding Task Feature to project", 'PENDING', 'HIGH', '20230608' , 8.5);


INSERT INTO tasks(task_name, task_status, task_priority, createDate, estdHrs) 
VALUES ("Incorporating code review comments", 'PENDING', 'HIGH', '20230608' , 3);



ALTER TABLE tasks modify column estdHrs DOUBLE NOT NULL;

SELECT * FROM tasks WHERE estdHrs = 8.5


SELECT * FROM tasks WHERE estdHrs = 3

ALTER TABLE tasks MODIFY COLUMN task_priority varchar(20);
 
 ALTER TABLE tasks MODIFY COLUMN task_status varchar(20);
 
 
 ALTER TABLE tasks 
 
 ADD CONSTRAINT CHK_Task_statis CHECK (task_status in ('PENDING','STARTED','COMPLETED','INPROGRESS','BLOCKED'));
 
 
 ALTER TABLE tasks 
 
 ADD CONSTRAINT CHK_Ttask_priority CHECK (task_priority in ('HIGH','MEDIUM','LOW'));
 
 