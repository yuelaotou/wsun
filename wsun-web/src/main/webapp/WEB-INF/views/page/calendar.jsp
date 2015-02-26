<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row-fluid">
	<div class="portlet box blue calendar">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>Calendar
			</div>
		</div>
		<div class="portlet-body light-grey">
			<div class="row-fluid">
				<div class="span3 responsive" data-tablet="span12 fix-margin" data-desktop="span8">
					<!-- BEGIN DRAGGABLE EVENTS PORTLET-->
					<h3 class="event-form-title">Draggable Events</h3>
					<div id="external-events">
						<form class="inline-form">
							<input type="text" value="" class="m-wrap span12" placeholder="Event Title..." id="event_title" /><br /> <a
								href="javascript:;" id="event_add" class="btn green">Add Event</a>
						</form>
						<hr />
						<div id="event_box"></div>
						<label for="drop-remove"> <input type="checkbox" id="drop-remove" />remove after drop
						</label>
						<hr class="visible-phone" />
					</div>
					<!-- END DRAGGABLE EVENTS PORTLET-->
				</div>
				<div class="span9">
					<div id="calendar" class="has-toolbar"></div>
				</div>
			</div>
			<!-- END CALENDAR PORTLET-->
		</div>
	</div>
</div>