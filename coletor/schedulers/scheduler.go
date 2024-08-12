package schedulers

import "time"

func Create(task func()) *Scheduler {
	scheduler := New(task)
	scheduler.Configure(5 * time.Second)
	return scheduler
}

func RunMany(schedulers []*Scheduler) {
	for _, scheduler := range schedulers {
		scheduler.Run()
	}
}

func CreateMany(tasks []func()) []*Scheduler {
	var schedulerList []*Scheduler
	for _, task := range tasks {
		schedulerList = append(schedulerList, Create(task))
	}
	return schedulerList
}
