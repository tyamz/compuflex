<?php

    namespace Compuflex\Contact\Classes;

    use Mail;
    use System\Models\MailTemplate;

    class SendMail {

		public static function sendNotification($post, $emails)
		{
			Mail::send('compuflex.contact::mail.message', $post, function($message) use ($emails, $post) {
				
				$message->to($emails);
				$message->from('mail@compuflexcorp.com', 'Compuflex Mail');
				$message->subject($post['subject']);
				$message->replyTo($post['email']);

			});
		}

        public static function sendAutoResponse($properties, $post) {

            $to      = $post[$properties['mail_resp_field']];
            $from    = $properties['mail_resp_from'];
            $subject = $properties['mail_resp_subject'];

            if(filter_var($to, FILTER_VALIDATE_EMAIL) && filter_var($from, FILTER_VALIDATE_EMAIL)) {

                # CUSTOM TEMPLATE
                $template = isset($properties['mail_resp_template']) && $properties['mail_resp_template'] != '' && MailTemplate::where('code', $properties['mail_resp_template'])->count() ? $properties['mail_resp_template'] : 'martin.forms::mail.autoresponse';

                Mail::sendTo($to, $template, $post, function($message) use ($from, $subject) {
                    $message->from($from);
                    $message->subject($subject);
                });

            }

        }

    }

?>