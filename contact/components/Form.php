<?php namespace Compuflex\Contact\Components;

use Cms\Classes\ComponentBase;
use Compuflex\Contact\Classes\SendMail;
use Compuflex\Contact\Models\Message;
use Compuflex\Contact\Models\Group;
use Illuminate\Support\Facades\Event;

class Form extends ComponentBase
{
    public function componentDetails()
    {
        return [
            'name'        => 'Contact Form',
            'description' => 'This component displays an AJAX form for front-end user(s) to submit inquiries.'
        ];
    }

    public function defineProperties()
    {
        return [];
    }
	
	public function onSubmit()
	{
		# FILTER ALLOWED FIELDS
		$allow = $this->property('allowed_fields');
		if(is_array($allow) && !empty($allow)) {
			foreach($allow as $field) {
				$post[$field] = post($field);
			}
		} else {
			$post = post();
		}
		
		# SAVE IN DATABASE
		$message = new Message;
		$message->sender_name = $post['name'];
		$message->sender_email = $post['email'];
		$message->subject = $post['subject'];
		$message->message = $post['message'];
		$message->recipient_group = $post['department'];
		$message->save();
		
		# SEND NOTIFICATION EMAIL
		if($this->property('mail_enabled')) {
			SendMail::sendNotification($this->getProperties(), $post, $message);
		}

		# SEND AUTORESPONSE EMAIL
		if($this->property('mail_resp_enabled')) {
			SendMail::sendAutoResponse($this->getProperties(), $post);
		}
        
		# FIRE AFTER SAVE EVENT
		Event::fire('compuflex.contact.afterSendMessage', [&$post]);
	}
	
	public function departments() {
		return Group::all();
	}
}
