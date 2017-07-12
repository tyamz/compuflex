<?php namespace Compuflex\Faqs\Models;

use Model;

/**
 * Model
 */
class Answer extends Model
{
    use \October\Rain\Database\Traits\Validation;

    use \October\Rain\Database\Traits\SoftDelete;

    protected $dates = ['deleted_at'];

    /*
     * Validation
     */
    public $rules = [
    ];

    /**
     * @var string The database table used by the model.
     */
    public $table = 'compuflex_faqs_answers';

    public $belongsTo = [
      'question' => 'Compuflex\Faqs\Models\Question'
    ];
}
