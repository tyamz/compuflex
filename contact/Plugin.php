<?php namespace Compuflex\Contact;

use System\Classes\PluginBase;
use Illuminate\Support\Facades\Event;
use Compuflex\Contact\Models\Message;

class Plugin extends PluginBase
{
	public function pluginDetails()
    {
        return [
            'name'        => 'Contact',
            'description' => 'Contact Us Form',
            'author'      => 'Thomas Yamakaitis (for The Compuflex Corporation - © 2017 The Compuflex Inc. All rights reserved.',
            'icon'        => 'icon-at'
        ];
    }
    public function registerComponents()
    {
		return [
			'Compuflex\Contact\Components\Form' => 'contactForm'
		];
    }

    public function registerSettings()
    {
    }
	
	public function boot()
	{
		Event::listen('compuflex.contact.beforeSendMessage', function ($form) {
			$message = new Message;
			$message->sender_name = $form['name'];
			$message->sender_email = $form['email'];
			$message->message = $form['message'];
			return true; // this will prevent sending email message
		});
	}
}
