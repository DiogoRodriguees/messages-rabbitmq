package schedulers

import (
	"log"
	"time"

	"github.com/algorythma/go-scheduler"
	"github.com/algorythma/go-scheduler/storage"
)

type Scheduler struct {
	TaskStore storage.NoOpStorage
	Scheduler scheduler.Scheduler
	Task      func()
}

func New(task func()) *Scheduler {
	return &Scheduler{
		TaskStore: storage.NoOpStorage{},
		Task:      task,
	}
}

func (s *Scheduler) Run() {
	log.Println("Executing task: ")
	s.Scheduler.Start()
}

func (s *Scheduler) Configure(interval time.Duration) {
	newScheduler := scheduler.New(s.TaskStore)
	newScheduler.RunEvery(interval, s.Task)
	s.Scheduler = newScheduler
}
